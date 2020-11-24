public class Product {
	public String name;
	public Integer weight;
	public Integer profit;
	public Integer discount;
	public Double price;
	public Double density;
	
	public void calculate() {
		this.price = (double) (this.profit - this.profit * (this.discount / 100.0));
		this.density = this.price / this.weight;
	}
}


