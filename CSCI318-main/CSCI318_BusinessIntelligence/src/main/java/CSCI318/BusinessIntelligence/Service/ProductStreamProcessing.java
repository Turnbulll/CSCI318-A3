package CSCI318.BusinessIntelligence.Service;

import CSCI318.BusinessIntelligence.Model.CustomerProductOrders;

/* This class computes a stream of brand quantities
 * and creates a state store for interactive queries.
 */

import CSCI318.BusinessIntelligence.Model.OrderEvent;
import CSCI318.BusinessIntelligence.Model.ProductQuantity;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.state.KeyValueStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Configuration
public class ProductStreamProcessing {

    public final static String STATE_STORE = "my-store";
    public final static String PRODUCT_CUSTOMER_STORE = "product-customer-store";

     @Bean
    public Function<KStream<?, OrderEvent>, KStream<String, ProductQuantity>> Process() {
        return inputStream -> {

            inputStream.map((k, v) -> {
                String customerId = v.getCustomerId();
                String productName = v.getProductName();
                int hashCode = v.hashCode();
                int quantity = v.getQuantity();
                double productPrice = v.getProductPrice();
                CustomerProductOrders customerProductOrder = new CustomerProductOrders(customerId, productName, productPrice, quantity);
                return KeyValue.pair(customerId + "_" + productName + "_" + hashCode, customerProductOrder);
            }).toTable(
                    Materialized.<String, CustomerProductOrders, KeyValueStore<Bytes, byte[]>>as(PRODUCT_CUSTOMER_STORE).
                            withKeySerde(Serdes.String()).
                            // a custom value serde for this state store
                            withValueSerde(customerProductOrderSerde())
            );

            KTable<String, Long> orderKTable = inputStream.
                    mapValues(OrderEvent::getProductName).
                    groupBy((keyIgnored, value) -> value).
                    count(
                            Materialized.<String, Long, KeyValueStore<Bytes, byte[]>>as(STATE_STORE).
                                    withKeySerde(Serdes.String()).
                                    withValueSerde(Serdes.Long())
                    );
            KStream<String, ProductQuantity> productQuantityStream = orderKTable.
                    toStream().
                    map((k, v) -> KeyValue.pair(k, new ProductQuantity(k, v)));
            // use the following code for testing
            productQuantityStream.print(Printed.<String, ProductQuantity>toSysOut().withLabel("Console Output"));

            return productQuantityStream;
        };
    }

     public Serde<CustomerProductOrders> customerProductOrderSerde() {
        final JsonSerde<CustomerProductOrders> customerProductOrdersJsonSerde = new JsonSerde<>();
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "CSCI318.BusinessIntelligence.Model.CustomerProductOrders");
        configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        customerProductOrdersJsonSerde.configure(configProps, false);
        return customerProductOrdersJsonSerde;
    }

}
