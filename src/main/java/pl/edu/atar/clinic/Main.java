package pl.edu.atar.clinic;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.api.runtime.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        // DOMYŚLNE PRZETWARZANIE - Pojedynczego pacjenta
        List<Patient> patients = new ArrayList<Patient>();
        patients.add(new Dataset().getPatients().get(0));

        KieServices kService = KieServices.Factory.get();
        KieContainer kContainer = kService.getKieClasspathContainer();

        // Baza wiedzy jest konfigurowana w /resources/META-INF/kmodule.xml
        KieBase kBase = kContainer.getKieBase("triage");
        StatelessKieSession kSession = kBase.newStatelessKieSession();

        for (Patient patient : patients) {

            LOGGER.info("Knowledge base created.\n");
            LOGGER.info("Information BEFORE reasoning.\n{}", patient.getPatientInformationLogger());

		    // LOGOWANIE WNIOSKOWANIA - Wariant pełny [CZERWONE LOGI]
            kSession.addEventListener(new DebugAgendaEventListener());
		    kSession.addEventListener(new DebugRuleRuntimeEventListener());

		    // LOGOWANIE WNIOSKOWANIA - Wariant uproszczony [CZARNE LOGI]
            kService.getLoggers().newConsoleLogger(kSession);
            KieRuntimeLogger logger = kService.getLoggers().newFileLogger(kSession, "./logs/reasoning_fact_" + patient.getId());

            LOGGER.info("... REASONING STARTED ...");
            kSession.execute(patient);
            logger.close();
            LOGGER.info("... REASONING ENDED ...\n");

            LOGGER.info("Information AFTER reasoning.\n{}", patient.getPatientInformationLogger());
        }
    }
}