part of 'employee_bloc.dart';

@immutable
abstract class EmployeeEvent {}

class SearchEmployeeEvent extends EmployeeEvent {
  final String keyword;
  
  SearchEmployeeEvent({required this.keyword});
}

class LoadEmployeeEvent extends EmployeeEvent {
  LoadEmployeeEvent();
}

class DeleteEmployeeEvent extends EmployeeEvent {
  final Employe employe;
  DeleteEmployeeEvent({ required this.employe});
}

class CreateEmployeeEvent extends EmployeeEvent {
  final Employe employe;
  CreateEmployeeEvent({ required this.employe});
}

class UpdateEmployeeEvent extends EmployeeEvent {
  final Employe employe;
  UpdateEmployeeEvent({ required this.employe});
}
