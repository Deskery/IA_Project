/*
Definit la classe des ressources de luxe
 */


public class LuxuryResource implements Resource{

    private float quantity;

    public LuxuryResource(float quantity) {
        this.quantity = quantity;
    }

    @Override
    public float getResourceQuantity() {
        return quantity;
    }
}
