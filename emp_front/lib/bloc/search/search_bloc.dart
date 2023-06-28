import 'dart:async';

import 'package:bloc/bloc.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:meta/meta.dart';

import '../employee/employee_bloc.dart';

part 'search_event.dart';
part 'search_state.dart';

class SearchBloc extends Bloc<SearchEvent, SearchState> {
  SearchBloc() : super(SearchInitial()) {
    on<OnChangeEvent>((event, emit) {
      state.isLoading = true;
      if (state.searchOnStoppedTyping != null) {
        state.searchOnStoppedTyping?.cancel();
      }

      if (event.searchController.value.text != '') {
        state.searchOnStoppedTyping = Timer(const Duration(seconds: 1), () {
          event.context.read<EmployeeBloc>().add(
              SearchEmployeeEvent(keyword: event.searchController.value.text));
          state.isLoading = false;
        });
      } else {
        event.context.read<EmployeeBloc>().add(LoadEmployeeEvent());
        state.isLoading = false;
      }

      emit(SearchState(
          isLoading: state.isLoading,
          searchOnStoppedTyping: state.searchOnStoppedTyping));
    });
  }
}
