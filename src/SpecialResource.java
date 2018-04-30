/*
Classe implementant les ressources spéciales
 */
public class SpecialResource implements Resource{
    private float quantity;

    public SpecialResource(float quantity) {
        this.quantity = quantity;
    }

    @Override
    public float getResourceQuantity() {
        return quantity;
    }
}
