part of 'employee_bloc.dart';

@immutable
abstract class EmployeeState {}

class EmployeeInitial extends EmployeeState {
  EmployeeInitial();
}

class SearchEmployeeSuccessState extends EmployeeState {
  final List<Employe> listEmployee;

  SearchEmployeeSuccessState({required this.listEmployee});
}

class SearchEmployeeLoadingState extends EmployeeState {}

class SearchEmployeeErrorState extends EmployeeState {
  final String errorMessage;

  SearchEmployeeErrorState({required this.errorMessage});
}

class LoadingEmployeeState extends EmployeeState {}

class LoadingEmployeeErrorState extends EmployeeState {
  final String errorMessage;

  LoadingEmployeeErrorState({required this.errorMessage});
}

class LoadingEmployeeSuccessState extends EmployeeState {
  final List<Employe> listEmployee;

  LoadingEmployeeSuccessState({required this.listEmployee});
}

class DeletedEmployeeState extends EmployeeState {}

class DeletingEmployeeState extends EmployeeState {}

class DeletingFailEmployeeState extends EmployeeState {
    final String errorMessage;

  DeletingFailEmployeeState({required this.errorMessage});
}

class CreatedEmployeeState extends EmployeeState {}

class CreatingEmployeeState extends EmployeeState {}

class CreatingFailEmployeeState extends EmployeeState {
    final String errorMessage;

  CreatingFailEmployeeState({required this.errorMessage});
}

class UpdatedEmployeeState extends EmployeeState {}

class UpdatingEmployeeState extends EmployeeState {}

class UpdatingFailEmployeeState extends EmployeeState {
    final String errorMessage;

  UpdatingFailEmployeeState({required this.errorMessage});
}