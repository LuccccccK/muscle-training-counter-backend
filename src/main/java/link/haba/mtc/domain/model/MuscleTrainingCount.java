package link.haba.mtc.domain.model;

import link.haba.mtc.domain.model.entity.MuscleTrainingCountEntity;

// 筋トレ回数 モデル
public class MuscleTrainingCount {
    public String selectedDate;
    public Integer countAbdominalMuscles;
    public Integer countPushUp;
    public Integer countSquat;

    public MuscleTrainingCount(MuscleTrainingCountEntity e) {
        this.selectedDate = e.date;
        this.countAbdominalMuscles = e.countAbdominalMuscles;
        this.countPushUp = e.countPushUp;
        this.countSquat = e.countSquat;
    }

    public MuscleTrainingCountEntity toEntity() {
        MuscleTrainingCountEntity e = new MuscleTrainingCountEntity();
        e.date = this.selectedDate;
        e.countAbdominalMuscles = this.countAbdominalMuscles;
        e.countPushUp = this.countPushUp;
        e.countSquat = this.countSquat;
        return e;
    }
}
