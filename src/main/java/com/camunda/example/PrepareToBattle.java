package com.camunda.example;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
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

        List<Boolean> army = new ArrayList<>(Collections.nCopies(warriors, true));

        System.out.println("enemyWarriors army " + enemyWarriors + " vs our army " + warriors);

        delegateExecution.setVariable("army", army);
        delegateExecution.setVariable("enemyWarriors", enemyWarriors);
    }
}
