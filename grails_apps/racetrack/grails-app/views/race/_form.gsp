<%@ page import="racetrack.Race" %>



<div class="fieldcontain ${hasErrors(bean: raceInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="race.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${raceInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: raceInstance, field: 'startDateTime', 'error')} required">
	<label for="startDateTime">
		<g:message code="race.startDateTime.label" default="Start Date Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="startDateTime" precision="day"  value="${raceInstance?.startDateTime}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: raceInstance, field: 'city', 'error')} required">
	<label for="city">
		<g:message code="race.city.label" default="City" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="city" required="" value="${raceInstance?.city}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: raceInstance, field: 'state', 'error')} required">
	<label for="state">
		<g:message code="race.state.label" default="State" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="state" from="${raceInstance.constraints.state.inList}" required="" value="${raceInstance?.state}" valueMessagePrefix="race.state"/>
</div>

<div class="fieldcontain ${hasErrors(bean: raceInstance, field: 'distance', 'error')} required">
	<label for="distance">
		<g:message code="race.distance.label" default="Distance" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="distance" value="${fieldValue(bean: raceInstance, field: 'distance')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: raceInstance, field: 'cost', 'error')} required">
	<label for="cost">
		<g:message code="race.cost.label" default="Cost" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="cost" value="${fieldValue(bean: raceInstance, field: 'cost')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: raceInstance, field: 'maxRunners', 'error')} required">
	<label for="maxRunners">
		<g:message code="race.maxRunners.label" default="Max Runners" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="maxRunners" type="number" value="${raceInstance.maxRunners}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: raceInstance, field: 'registrations', 'error')} ">
	<label for="registrations">
		<g:message code="race.registrations.label" default="Registrations" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${raceInstance?.registrations?}" var="r">
    <li><g:link controller="registration" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="registration" action="create" params="['race.id': raceInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'registration.label', default: 'Registration')])}</g:link>
</li>
</ul>

</div>

