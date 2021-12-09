package br.com.klever.openweathermap.suite;
import static org.hamcrest.Matchers.*;

import br.com.klever.openweathermap.testes.Climas;

import org.junit.runner.RunWith;


@RunWith(org.junit.runners.Suite.class)
@org.junit.runners.Suite.SuiteClasses({
        Climas.class
})

public class Suite {


}

