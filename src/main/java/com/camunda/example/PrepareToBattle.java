package com.camunda.example;

import com.camunda.example.domain.Warrior;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class PrepareToBattle implements JavaDelegate {
    @Value("${maxWarriors}")
    private int maxWarriors;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        int warriors = (int) delegateExecution.getVariable("warriors");
        int enemyWarriors = (int) (Math.random() * 100);

        if (warriors < 1 || warriors > maxWarriors) {
            throw new BpmnError("warriorsError");
        }

//        List<Boolean> army = new ArrayList<>(Collections.nCopies(warriors, true));
        List<Warrior> army = new ArrayList<>();

        for (int i = 0; i < warriors; i++) {
            army.add(createWarrior(i));
        }

        System.out.println("enemyWarriors army " + enemyWarriors + " vs our army " + warriors);
        ObjectValue armyJson = Variables.objectValue(army).serializationDataFormat("application/json").create();
        delegateExecution.setVariable("army", army);
        delegateExecution.setVariable("armyJson", armyJson);
        delegateExecution.setVariable("enemyWarriors", enemyWarriors);
    }

    private Warrior createWarrior(int i) {
        Warrior warrior = new Warrior();
        warrior.setName("Ivan " + i);
        warrior.setTitle("Title" + i);
        warrior.setHp((int) (Math.random() * 1000));
        warrior.setAlive(true);
        return warrior;
    }
}
