package com.tribal

class Project {

	String projectName
	String projectCode
	String techLead
	String projectManager
	Date deliveryDate
	ProjectPhases currentPhase
	int priority
	
	
    static constraints = {
		projectName( matches: "[a-zA-Z0-9]+", blank: false)
		projectCode( matches: "[a-zA-Z0-9]+", blank: false)
		deliveryDate( min: new Date())
		priority(matches: "[0-9]", unique: true, min: 1, validator: { val, obj -> 
			if(val > Project.count +1) return ['failed.constraint.maxpriority'] })
    }
}
