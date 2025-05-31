public class WorkoutData {
    private String startTime;
    private String endTime;
    private String muscleGroup;
    private int cardioTime;

    public WorkoutData(String startTime, String endTime, String muscleGroup, int cardioTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.muscleGroup = muscleGroup;
        this.cardioTime = cardioTime;
    }

    public String getStartTime() { 
    	return startTime;
    }
    public String getEndTime() { 
    	return endTime; 
    }
    public String getMuscleGroup() { 
    	return muscleGroup; 
    }
    public int getCardioTime() { 
    	return cardioTime; 
    }

    public int getDuration() {
        String[] start = startTime.split(":");
        String[] end = endTime.split(":");
        int startMin = Integer.parseInt(start[0]) * 60 + Integer.parseInt(start[1]);
        int endMin = Integer.parseInt(end[0]) * 60 + Integer.parseInt(end[1]);
        return endMin - startMin;
    }
}

