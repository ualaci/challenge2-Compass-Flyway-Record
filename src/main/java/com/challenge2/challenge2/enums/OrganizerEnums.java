package com.challenge2.challenge2.enums;

public enum OrganizerEnums {
    ScrumMaster,
    Coordinator,
    Instructor;

    public static OrganizerEnums getDefault() {
        return ScrumMaster;
    }
}
