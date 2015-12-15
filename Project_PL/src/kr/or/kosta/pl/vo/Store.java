package kr.or.kosta.pl.vo;

import java.io.Serializable;

public class Store implements Serializable {
	private int storeId;
	private String storeName;
	private String ownerId;
	private String storeAddress;
	private String storePhone;
	private String storeLicense;
	private String ownerName;
	private String ownerPhone;
	private int storeItemCount;
	private String storeTimestamp;
	private String storeKey;
	



	public Store() {
	}

	public Store(int storeId, String storeName, String ownerId, String storeAddress, String storePhone,
			String storeLicense) {
		super();
		this.storeId = storeId;
		this.storeName = storeName;
		this.ownerId = ownerId;
		this.storeAddress = storeAddress;
		this.storePhone = storePhone;
		this.storeLicense = storeLicense;
	}

	public Store(int storeId, String storeName, String ownerId, String storeAddress, String storePhone,
			String storeLicense, String ownerName, String ownerPhone) {
		super();
		this.storeId = storeId;
		this.storeName = storeName;
		this.ownerId = ownerId;
		this.storeAddress = storeAddress;
		this.storePhone = storePhone;
		this.storeLicense = storeLicense;
		this.ownerName = ownerName;
		this.ownerPhone = ownerPhone;
	}
	
	public Store(int storeId, String storeName, String ownerId, String storeAddress, String storePhone,
			String storeLicense, String ownerName, String ownerPhone, String storeTimestamp, String storeKey) {
		super();
		this.storeId = storeId;
		this.storeName = storeName;
		this.ownerId = ownerId;
		this.storeAddress = storeAddress;
		this.storePhone = storePhone;
		this.storeLicense = storeLicense;
		this.storeTimestamp = storeTimestamp;
		this.storeKey = storeKey;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public String getStorePhone() {
		return storePhone;
	}

	public void setStorePhone(String storePhone) {
		this.storePhone = storePhone;
	}

	public String getStoreLicense() {
		return storeLicense;
	}

	public void setStoreLicense(String storeLicense) {
		this.storeLicense = storeLicense;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerPhone() {
		return ownerPhone;
	}

	public void setOwnerPhone(String ownerPhone) {
		this.ownerPhone = ownerPhone;
	}
	
	public String getStoreTimestamp() {
		return storeTimestamp;
	}

	public void setStoreTimestamp(String storeTimestamp) {
		this.storeTimestamp = storeTimestamp;
	}

	public String getStoreKey() {
		return storeKey;
	}

	public void setStoreKey(String storeKey) {
		this.storeKey = storeKey;
	}
	public int getStoreItemCount() {
		return storeItemCount;
	}

	public void setStoreItemCount(int storeItemCount) {
		this.storeItemCount = storeItemCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
		result = prime * result + ((ownerName == null) ? 0 : ownerName.hashCode());
		result = prime * result + ((ownerPhone == null) ? 0 : ownerPhone.hashCode());
		result = prime * result + ((storeAddress == null) ? 0 : storeAddress.hashCode());
		result = prime * result + storeId;
		result = prime * result + storeItemCount;
		result = prime * result + ((storeKey == null) ? 0 : storeKey.hashCode());
		result = prime * result + ((storeLicense == null) ? 0 : storeLicense.hashCode());
		result = prime * result + ((storeName == null) ? 0 : storeName.hashCode());
		result = prime * result + ((storePhone == null) ? 0 : storePhone.hashCode());
		result = prime * result + ((storeTimestamp == null) ? 0 : storeTimestamp.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Store other = (Store) obj;
		if (ownerId == null) {
			if (other.ownerId != null)
				return false;
		} else if (!ownerId.equals(other.ownerId))
			return false;
		if (ownerName == null) {
			if (other.ownerName != null)
				return false;
		} else if (!ownerName.equals(other.ownerName))
			return false;
		if (ownerPhone == null) {
			if (other.ownerPhone != null)
				return false;
		} else if (!ownerPhone.equals(other.ownerPhone))
			return false;
		if (storeAddress == null) {
			if (other.storeAddress != null)
				return false;
		} else if (!storeAddress.equals(other.storeAddress))
			return false;
		if (storeId != other.storeId)
			return false;
		if (storeItemCount != other.storeItemCount)
			return false;
		if (storeKey == null) {
			if (other.storeKey != null)
				return false;
		} else if (!storeKey.equals(other.storeKey))
			return false;
		if (storeLicense == null) {
			if (other.storeLicense != null)
				return false;
		} else if (!storeLicense.equals(other.storeLicense))
			return false;
		if (storeName == null) {
			if (other.storeName != null)
				return false;
		} else if (!storeName.equals(other.storeName))
			return false;
		if (storePhone == null) {
			if (other.storePhone != null)
				return false;
		} else if (!storePhone.equals(other.storePhone))
			return false;
		if (storeTimestamp == null) {
			if (other.storeTimestamp != null)
				return false;
		} else if (!storeTimestamp.equals(other.storeTimestamp))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Store [storeId=" + storeId + ", storeName=" + storeName + ", ownerId=" + ownerId + ", storeAddress="
				+ storeAddress + ", storePhone=" + storePhone + ", storeLicense=" + storeLicense + ", ownerName="
				+ ownerName + ", ownerPhone=" + ownerPhone + ", storeItemCount=" + storeItemCount + ", storeTimestamp="
				+ storeTimestamp + ", storeKey=" + storeKey + "]";
	}

	
	
}