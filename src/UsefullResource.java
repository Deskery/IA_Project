/*
Classe implementant la notion de resource utile
 */


public class UsefullResource implements Resource {
    private float quantity;

    public UsefullResource(float quantity) {
        this.quantity = quantity;
    }

    @Override
    public float getResourceQuantity() {
        return quantity;
    }
}
