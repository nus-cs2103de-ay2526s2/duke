package model;

import ui.Ui;

/**
 * Task has 2 states: done and not done
 * Events are tasks with start and end date/time
 */
public class Task {
    public final String name;
    public boolean isDone = false;

    // Constructor
    public Task(String name){
        this.name = name;
    }

    // Returns a string containing task status
    public String toString(){
        String doneStatus = isDone ? "[X]" : "[ ]";
        // returns [T][X] task name
        return "[T]" + doneStatus + " " + this.name;
    }

    // Marks task as done
    public void markAsDone (){
        if (this.isDone) {
            System.out.println(Ui.BOT_LABEL + "This task has already been done!");
        }
        else {
            this.isDone = true;
        }
    }

    // Unmarks task as done
    public void unmarkAsDone (){
        if (isDone) {
            this.isDone = false;
        }
        else {
            this.isDone = true;
            System.out.println(Ui.BOT_LABEL + "This task is still waiting for you!");
        }
    }
}