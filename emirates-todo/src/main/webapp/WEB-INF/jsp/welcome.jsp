<div class="container">
	<div class="row">
				<input type="text" class="form-control add-todo"
					placeholder="Add todo" ng-keydown='addTodo($event)'
					ng-model='addtodo'>
				<hr>
		<div class="col-md-6">
			<div class="todolist not-done">				
				<h4>New Todos in Pending State</h4>
				<ul id="sortable" class="list-unstyled" ng-repeat="m in response">
					<li class="ui-state-default">
						<div class="checkbox">
							<div class="alert alert-danger">
								<input id="{{m.id}}" type="checkbox" ng-model='selectedTodoId'
									data-ng-click='selectTodo(m.id)' /><strong>{{m.desc}}</strong>&nbsp;
								<button id="completed" class="btn btn-success"
									ng-click='completeTodo()'>Completed</button>
								&nbsp;
								<button id="delete" class="btn btn-danger"
									ng-click='deleteTodo()'>Delete</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
		<div class="col-md-6">
			<div class="todolist">
				<h4>Completed Todos</h4>
				<ul id="sortable" class="list-unstyled" ng-repeat="m in completed">
					<li class="ui-state-default">
						<div class="checkbox">
							<div class="alert alert-success">
								<strong>{{m.desc}}</strong>&nbsp;
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<button id="reset" class="btn btn-success"
									ng-click='resetAll()'>Reset Application</button>
</div>