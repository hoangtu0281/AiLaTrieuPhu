package com.example.hoangtu.ailatrieuphu;


public class Question {
    private String question;
    private int level, trueCase;
    private String caseA, caseB, caseC, caseD;

    public Question(String question, int level, String caseA,
                    String caseB, String caseC, String caseD,
                    int trueCase) {
        this.question = question;
        this.level = level;
        this.caseA = caseA;
        this.caseB = caseB;
        this.caseC = caseC;
        this.caseD = caseD;
        this.trueCase = trueCase;
    }

    public String getQuestion() {
        return question;
    }

    public int getLevel() {
        return level;
    }

    public String getCaseA() {
        return caseA;
    }

    public String getCaseB() {
        return caseB;
    }

    public String getCaseC() {
        return caseC;
    }

    public String getCaseD() {
        return caseD;
    }

    public int getTrueCase() {
        return trueCase;
    }

    @Override
    public String toString() {
        return  question+"\n"+
                "Level: "+level+
                "A:"+caseA+"\n"+
                "B:"+caseB+"\n"+
                "C:"+caseC+"\n"+
                "D:"+caseD+"\n"+
                "Answer:"+trueCase+"\n";
    }
}
