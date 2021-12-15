package CSCI318.BusinessIntelligence.Service;

import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.cloud.stream.binder.kafka.streams.InteractiveQueryService;
import org.springframework.stereotype.Service;

import CSCI318.BusinessIntelligence.Model.CustomerProductOrders;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductInteractiveQuery {

    private InteractiveQueryService interactiveQueryService;

    //@Autowired
    public ProductInteractiveQuery(InteractiveQueryService interactiveQueryService) {
        this.interactiveQueryService = interactiveQueryService;
    }

    public long getProductQuantity(String productName) {
        if (keyValueStore().get(productName) != null) {
            return keyValueStore().get(productName);
            //long quantity = keyValueStore().get(productName);
            //return new BrandQuantity(productName, quantity);
        } else {
            throw new NoSuchElementException(); //TODO: should use a customised exception.
        }
    }

    public List<String> getProductNames() {
        List<String> productList = new ArrayList<>();
        KeyValueIterator<String, Long> all = keyValueStore().all();
        while (all.hasNext()) {
            String next = all.next().key;
            productList.add(next);
        }
        return productList;
    }

    public List<String> getAllProductForCustomer(String customerID) {
        List<String> productList = new ArrayList<>();
        KeyValueIterator<String, CustomerProductOrders> all = productCustomerStore().all();
        while (all.hasNext()) {
            CustomerProductOrders customerProductOrder = all.next().value;
            String customer_ID = customerProductOrder.getCustomerID();
            String productName = customerProductOrder.getProductName();

            if (customer_ID.equals(customerID)) {
                productList.add(productName);
            }
        }
        return productList;
    }

    public double getTotalOrderValueForCustomer(String customerID) {
        double totalOrderValue = 0;
        KeyValueIterator<String, CustomerProductOrders> all = productCustomerStore().all();
        while (all.hasNext()) {
            CustomerProductOrders customerProductOrder = all.next().value;
            String customer_ID = customerProductOrder.getCustomerID();
            int productQuantity = customerProductOrder.getQuantity();
            double productPrice = customerProductOrder.getPrice();

            if (customer_ID.equals(customerID)) {
                totalOrderValue += productQuantity * productPrice;
            }
        }
        return totalOrderValue;
    }

    private ReadOnlyKeyValueStore<String, Long> keyValueStore() {
        return this.interactiveQueryService.getQueryableStore(ProductStreamProcessing.STATE_STORE,
                QueryableStoreTypes.keyValueStore());
    }

    private ReadOnlyKeyValueStore<String, CustomerProductOrders> productCustomerStore() {
        return this.interactiveQueryService.getQueryableStore(ProductStreamProcessing.PRODUCT_CUSTOMER_STORE,
                QueryableStoreTypes.keyValueStore());
    }
}
