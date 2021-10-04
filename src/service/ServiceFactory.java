package service;

public class ServiceFactory {

	public static ItemService createItemService() {
		return new ItemServiceDBImpl();
	}

}
