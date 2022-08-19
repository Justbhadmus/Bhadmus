package testcasesapi;

import jsonread.JsonStore;
import org.testng.annotations.Test;

public class JsonRunTime {
    JsonStore runner = new JsonStore();

    @Test(priority = 0)
    public void printEmail(){

        runner.printUsernameToConsole();

    }

    @Test(priority = 1)
     public void checkIDIsInRange(){

        runner.getUSerIDAndVerifyRange();

    }

    @Test(priority = 2)
    public void verifyNonEmptyPost(){

        runner.postNonEmptyComment();

    }
}

