package block1.javaGame.verifyers;

public abstract class Verifier {

    private BooleanMethod[] conditions;

    protected interface BooleanMethod {
        boolean call(Object[] args);
    }

    public Verifier() {
        conditions = createConditions();
    }

    public abstract BooleanMethod[] createConditions();

    public boolean check(Object... args) {
        for (BooleanMethod m : conditions) {
            if (!m.call(args)) {
                return false;
            }
        }
        return true;
    }
}