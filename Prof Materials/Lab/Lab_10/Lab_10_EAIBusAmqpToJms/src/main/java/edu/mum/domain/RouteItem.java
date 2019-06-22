  

package edu.mum.domain;


import java.io.Serializable;

public class RouteItem  implements Serializable {

    public enum RouteItemType {HIGH_PRICE, MODERATE_PRICE}

    private Item item;
    private String auctionId;
    private final RouteItemType itemType;

    /**
     * Constructor
     */
    public RouteItem(Item item, RouteItemType itemType) {
         this.itemType = itemType;
        this.item  = item;
        this.auctionId = item.getName() + item.getVersion();
    }

 
    public String getAuctionId() {
		return auctionId;
	}


	public void setAuctionId(String auctionId) {
		this.auctionId = auctionId;
	}


	/**
     * Gets item type.
     */
    public RouteItemType getRouteItemType() {
        return itemType;
    }

	public Item getItem() {
		return item;
	}

 }
