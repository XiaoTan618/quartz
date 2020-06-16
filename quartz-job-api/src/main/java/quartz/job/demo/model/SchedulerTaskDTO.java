package quartz.job.demo.model;

import java.io.Serializable;

/**
 * @author Tan
 * 任务调度处理的类
 */
public class SchedulerTaskDTO  implements Serializable {


    private String taskId;


    private String triggerName;


    private String triggerGroup;


    private String cornExpression;


    private boolean skipFirstExecute;



    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    public String getTriggerGroup() {
        return triggerGroup;
    }

    public void setTriggerGroup(String triggerGroup) {
        this.triggerGroup = triggerGroup;
    }

    public String getCornExpression() {
        return cornExpression;
    }

    public void setCornExpression(String cornExpression) {
        this.cornExpression = cornExpression;
    }

    public boolean isSkipFirstExecute() {
        return skipFirstExecute;
    }

    public void setSkipFirstExecute(boolean skipFirstExecute) {
        this.skipFirstExecute = skipFirstExecute;
    }

}
