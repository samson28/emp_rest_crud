import 'package:bloc/bloc.dart';
import 'package:emp_front/model/employee.dart';
import 'package:emp_front/repository/employee_repository.dart';
import 'package:flutter/foundation.dart';
import 'package:meta/meta.dart';

part 'employee_event.dart';
part 'employee_state.dart';

class EmployeeBloc extends Bloc<EmployeeEvent, EmployeeState> {
  EmployeRepo employeRepo = EmployeRepo();

  EmployeeBloc() : super(EmployeeInitial()) {
    on<LoadEmployeeEvent>(_onLoadEmployeeEvent);

    on<DeleteEmployeeEvent>(_onDeleteEmployeeEvent);

    on<CreateEmployeeEvent>(_onCreateEmployeeEvent);

    on<UpdateEmployeeEvent>(_onUpdateEmployeeEvent);

    on<SearchEmployeeEvent>(_onSearchEmployeeEvent);
  }

  Future<void> _onLoadEmployeeEvent(
      LoadEmployeeEvent event, Emitter<EmployeeState> emit) async {
    emit(LoadingEmployeeState());
    try {
      List<Employe> listEmployee = await employeRepo.getEmploye();
      emit(LoadingEmployeeSuccessState(listEmployee: listEmployee));
    } catch (e) {
      emit(LoadingEmployeeErrorState(errorMessage: e.toString()));
    }
  }

  Future<void> _onDeleteEmployeeEvent(
      DeleteEmployeeEvent event, Emitter<EmployeeState> emit) async {
    try {
      await employeRepo.deleteEmploye(event.employe);
      emit(DeletedEmployeeState());
    } catch (e) {
      emit(DeletingFailEmployeeState(errorMessage: e.toString()));
    }
  }

  Future<void> _onCreateEmployeeEvent(
      CreateEmployeeEvent event, Emitter<EmployeeState> emit) async {
    try {
      await employeRepo.addEmploye(event.employe);
      emit(CreatedEmployeeState());
    } catch (e) {
      emit(CreatingFailEmployeeState(errorMessage: e.toString()));
    }
  }

  Future<void> _onUpdateEmployeeEvent(
      UpdateEmployeeEvent event, Emitter<EmployeeState> emit) async {
    try {
      await employeRepo.updateEmploye(event.employe);
      emit(UpdatedEmployeeState());
    } catch (e) {
      emit(UpdatingFailEmployeeState(errorMessage: e.toString()));
    }
  }

  Future<void> _onSearchEmployeeEvent(
      SearchEmployeeEvent event, Emitter<EmployeeState> emit) async {
    emit(SearchEmployeeLoadingState());
    try {
      List<Employe> listEmployee =
          await employeRepo.shearchEmploye(event.keyword);
      emit(SearchEmployeeSuccessState(listEmployee: listEmployee));
    } catch (e) {
      emit(SearchEmployeeErrorState(errorMessage: e.toString()));
    }
  }
}
