package org.nobel.highriseapi.resources;

import java.util.Collections;
import java.util.List;

import org.nobel.highriseapi.HighriseClientConfig;
import org.nobel.highriseapi.entities.Deal;
import org.nobel.highriseapi.entities.lists.DealList;
import org.nobel.highriseapi.resources.base.EntityCacheProvider;
import org.nobel.highriseapi.resources.base.EntityCacheProvider.EntityCache;
import org.nobel.highriseapi.resources.base.EntityIdComparator;
import org.nobel.highriseapi.resources.base.EntityResource;
import org.nobel.highriseapi.resources.base.RestResourceConfig;

public class DealResource extends EntityResource<Deal> {
	
	 public enum DealKind {
	           DEAL_USERS("deals.xml?filter[created_since]=year&filter[user_id]={id}");

	        public String resourceUrl;

	        DealKind(String resourceUrl) {
	            this.resourceUrl = resourceUrl;
	        }
	    }

    public DealResource(HighriseClientConfig clientConfig, EntityCacheProvider entityCacheProvider) {
        super(clientConfig, entityCacheProvider);
    }
    
    public List<Deal> getAll(DealKind dealKind, int id) {

        Class<?> type = getEntityListConfig().type;
        String url = buildResourceUrl(getBaseUrl(), dealKind.resourceUrl);
        EntityCache cache = getCache(dealKind.name());
        url = replaceVariablesInUrl(url, createIdVariableReplacement(id));

        List<Deal> dealList = new RemoteEntityAccessorWithCacheSupport<Deal>(url, type, cache, getClientConfig()
                .isUseCache()).getEntityList();
        Collections.sort(dealList, new EntityIdComparator());

        return dealList;
    }

    @Override
    protected RestResourceConfig getEntityConfig() {
        return createResourceConfig("deals/{id}.xml", Deal.class);
    }

    @Override
    protected RestResourceConfig getEntityListConfig() {
        return createResourceConfig("deals.xml", DealList.class);
    }

}
