package lk.ijse.pos_system_nike.bo;

import lk.ijse.pos_system_nike.bo.custom.impl.CustomerBOImpl;
import lk.ijse.pos_system_nike.bo.custom.impl.ItemBOImpl;
import lk.ijse.pos_system_nike.bo.custom.impl.PlaceOrderBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {}

    public static BOFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes{
        CUSTOMER,ITEM,PLACEORDER
    }

    public SuperBO getBO(BOTypes type) {
        switch (type) {
            case CUSTOMER:
                return new CustomerBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case PLACEORDER:
               return new PlaceOrderBOImpl();
            default:
                return null;
        }
    }
}
