package bootstrap;

import models.User;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;

/**
 * Created by luhaoran on 7/5/17.
 */

@OnApplicationStart
public class LoadFixtures extends Job {

    public void doJob() {
        //Load default data if the database is empty
        if (User.count() == 0) {
            System.out.println("loading data from fakedata.yml");
            Fixtures.loadModels("fakeData.yml");
        }
    }
}
