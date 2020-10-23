import java.util.ArrayList;

public class Winner {

    private String name;
    private ArrayList<Integer> amounts = new ArrayList<>();

    public Winner(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addAmount( int amount ) {
        this.amounts.add( amount );
    }

    public int getAmountTotal() {
        int total = 0;

        for( int i = 0; i < this.amounts.size(); ++i ) {
            total += this.amounts.get( i );
        }

        return total;
    }

    @Override
    public String toString() {
        String response = this.name + ":";

        for( int i = 0; i < this.amounts.size(); ++i ) {
            response += Integer.toString( this.amounts.get(i) );

            if( i != ( this.amounts.size() - 1 ) ) {
                response += ",";
            }
        }

        return (response + "\n");
    }
}
