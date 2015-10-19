package block1.javaGame.verifyers;

import java.util.regex.Pattern;

public class GameVerifier extends Verifier {

    @Override
    public BooleanMethod[] createConditions() {
        BooleanMethod[] toReturn = new BooleanMethod[2];
        toReturn[0] = (args) -> Pattern.compile("-?\\p{Digit}+").matcher((String) args[0]).matches();
        toReturn[1] = (args) -> {
            int input = Integer.parseInt((String) args[0]);
            return input >= (Integer) args[1] && input <= (Integer) args[2];
        };
        return toReturn;
    }
}