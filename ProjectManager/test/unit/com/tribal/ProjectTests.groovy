package com.tribal



import javax.validation.constraints.AssertTrue;

import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Project)
class ProjectTests {

    void testInstanceConstrainstPositiveFlow() {
		
		def instance = new Project(	projectName: "Project1",
									projectCode: "PC1",
									techLead: "tech1",
									projectManager: "lead1",
									deliveryDate: new Date()+1,
									currentPhase: ProjectPhases.DEVELOPMENT,
									priority: 1)
		
		instance.validate()
		assertFalse(instance.hasErrors())
		
    }
	
	void testInstanceConstrainstCustomValidadtor() {
		
		def instance1 = new Project(projectName: "Project1",
									projectCode: "PC1",
									techLead: "tech1",
									projectManager: "lead1",
									deliveryDate: new Date()+1,
									currentPhase: ProjectPhases.DEVELOPMENT,
									priority: 1)

		def instance2 = new Project(projectName: "Project1",
									projectCode: "PC1",
									techLead: "tech1",
									projectManager: "lead1",
									deliveryDate: new Date()+1,
									currentPhase: ProjectPhases.DEVELOPMENT,
									priority: 2)
		
		def instance3 = new Project(projectName: "Project1",
									projectCode: "PC1",
									techLead: "tech1",
									projectManager: "lead1",
									deliveryDate: new Date()+1,
									currentPhase: ProjectPhases.DEVELOPMENT,
									priority: 4)
		instance1.save()
		instance2.save()
		instance3.save()
		
		instance1.validate()
		instance2.validate()
		instance3.validate()
		
		assertFalse(instance1.hasErrors())
		assertFalse(instance2.hasErrors())
		assertTrue(instance3.hasErrors())
		
		//assertTrue(instance3.errors.hasProperty("failed.constraint.maxpriority"))
	}
}
