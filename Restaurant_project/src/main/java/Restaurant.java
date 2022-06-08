import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        LocalTime currTime = this.getCurrentTime();
        int compareCurrTimeWithOpenTime  = currTime.compareTo(openingTime);
        int compareCurrTimeWithCloseTime = currTime.compareTo(openingTime);
        if (compareCurrTimeWithOpenTime >=0 && compareCurrTimeWithCloseTime <=0) {
        	return true;
        }
        else {
        	return false;
        }
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
        if (menu.size() == 0)
        	return null;
        else 
        	return menu;
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }
    private int getOrderTotal(List<Item>list) {
	 int totalBill = 0;
	 for (Item item: list) {
		totalBill = totalBill + item.price;
	 }
	 return totalBill;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }
    public int selectItems(Scanner sc) {
	    System.out.println("See Menu and Select Items");
	    System.out.println("Press 1: to select item 2: to remove item 3:finish Order 4: to give item a pass");
	    List<Item>selectedItems = new ArrayList<Item>();
	    if (sc == null)
		    sc = new Scanner(System.in);
	    int totalBill = 0;
	    int inp = 0;
	    while (1) {
		for (Item item : menu) {
			System.out.println("Item name"+ " "+item.name + "Item Price"+ " "+item.price);
			inp = sc.nextInt();
			switch (inp) {
				case 1	: 
					selectedItems.add(item);
					break;
				case 2	: 
					if (selectedItems.contains(item))
						selectedItems.remove(item);
					break;
				case 3	:
				case 4	:
					 break;
				default :
					break;
			}
			if (inp == 3)
				break;
	    }
	    if (inp == 3)
		    break;
    } 
   totalBill =  getOrderTotal(selectedItems);
   return totalBill;
}
