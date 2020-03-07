package ch.wesr.ecommerce.config;

import ch.wesr.ecommerce.entity.Product;
import ch.wesr.ecommerce.entity.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.Set;

@Configuration
public class EcommerceDateRestConfig implements RepositoryRestConfigurer {

    @Autowired
    private EntityManager em;




    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        HttpMethod[] theUnsupportedActions = {HttpMethod.DELETE, HttpMethod.POST, HttpMethod.PUT};

        // disable Http Methods for Product
        config.getExposureConfiguration()
                .forDomainType(Product.class)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));

        // disable Http Methods for ProductCategory
        config.getExposureConfiguration()
                .forDomainType(ProductCategory.class)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));

        // call an internal helper method to expose the entity ids
        // because by default the spring data rest does not expose the entity ids
        exposeIds(config);
    }

    private void exposeIds(RepositoryRestConfiguration config) {
        // expose entity ids

        // - get a list of all entity classes from the entity manager
        Set<EntityType<?>> entities = em.getMetamodel().getEntities();
        // create an array of the entitiy types
        ArrayList<Object> entityClasses = new ArrayList<>();
        // get the entity types for the entities
        for (EntityType<?> entity : entities) {
            entityClasses.add(entity.getJavaType());
        }
        // expose the entity ids for the array of entity/domain types
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }

}
