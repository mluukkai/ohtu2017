
package statistics.matcher;

import statistics.Player;

public class Or implements Matcher {
    private Matcher[] matchers;
    
    public Or(Matcher... matchers) {
        this.matchers = matchers;
    }

    @Override
    public boolean matches(Player p) {
        for(int i = 0; i < matchers.length; i++){
            if(matchers[i].matches(p)){
                return true;
            }
        }
        return false;
    }
    
    
    
    
}
