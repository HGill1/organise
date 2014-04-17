<div class="header-tasks">
	<span class="tasks-name" data-control="tasksMenuBtn">Tasks <span
		class="${bubbleClass}">${todaysTasksPending}</span>
	</span>
	<div class="tasks-dropdown hide" data-control="tasksMenu">
		<div class="module-title-border">Task Management</div>
		<div class="module-table">
			<input type="hidden" id="pastDueTasks" value="false"> <input
				type="hidden" id="dueTodayTasks" value="false"> <input
				type="hidden" id="urgentTasks" value="false"> <input
				type="hidden" id="todayPendingTasks" value="false"> <input
				type="hidden" id="displayAllUsersTasks" value="false"> <a
				id="${displayOldTasksPending}" href="${displayOldTasksPending}"
				class="${oldTasksPendingClass}">${oldTasksPendingSpan}</a> <a
				id="${displayTodaysDueTasks}" href="displayTodaysDueTasks"
				class="${todaysDueTasksClass}">${todaysDueTasksSpan}</a> <a
				id="${displayUrgentTasks}" href="${displayUrgentTasks}">${urgentTasksSpan}</a> <a
				id="${displayTodaysTasksPending}" href="displayTodaysTasksPending"
				class="${todaysTasksPendingClass}">${todaysTasksPendingSpan}</a> <a
				id="displayAllMyTasks" href="displayAllMyTasks">Total <span>${displayAllTasks} tasks</span></a>
		</div>
	</div>
</div>