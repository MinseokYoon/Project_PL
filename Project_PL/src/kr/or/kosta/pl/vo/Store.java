package kr.or.kosta.pl.vo;

import java.io.Serializable;

public class Store implements Serializable{
   private int storeId;
   private String storeName;
   private String ownerId;
   private String storeAddress;
   private String storePhone;
   private String storeLicense;
   private Owner owner;
   
   public Store(){}

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
         String storeLicense, Owner owner) {
      super();
      this.storeId = storeId;
      this.storeName = storeName;
      this.ownerId = ownerId;
      this.storeAddress = storeAddress;
      this.storePhone = storePhone;
      this.storeLicense = storeLicense;
      this.owner = owner;
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

   public Owner getOwner() {
      return owner;
   }

   public void setOwner(Owner owner) {
      this.owner = owner;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((owner == null) ? 0 : owner.hashCode());
      result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
      result = prime * result + ((storeAddress == null) ? 0 : storeAddress.hashCode());
      result = prime * result + storeId;
      result = prime * result + ((storeLicense == null) ? 0 : storeLicense.hashCode());
      result = prime * result + ((storeName == null) ? 0 : storeName.hashCode());
      result = prime * result + ((storePhone == null) ? 0 : storePhone.hashCode());
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
      if (owner == null) {
         if (other.owner != null)
            return false;
      } else if (!owner.equals(other.owner))
         return false;
      if (ownerId == null) {
         if (other.ownerId != null)
            return false;
      } else if (!ownerId.equals(other.ownerId))
         return false;
      if (storeAddress == null) {
         if (other.storeAddress != null)
            return false;
      } else if (!storeAddress.equals(other.storeAddress))
         return false;
      if (storeId != other.storeId)
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
      return true;
   }

   @Override
   public String toString() {
      return "Store [storeId=" + storeId + ", storeName=" + storeName + ", ownerId=" + ownerId + ", storeAddress="
            + storeAddress + ", storePhone=" + storePhone + ", storeLicense=" + storeLicense + ", owner=" + owner
            + "]";
   }
   
   
}