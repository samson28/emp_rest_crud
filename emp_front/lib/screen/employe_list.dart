import 'dart:async';

import 'package:emp_front/bloc/employee/employee_bloc.dart';
import 'package:emp_front/bloc/search/search_bloc.dart';
import 'package:emp_front/widget/employe_list_widget.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

class EmployeList extends StatefulWidget {
  const EmployeList({super.key});

  @override
  State<EmployeList> createState() => _EmployeListState();
}

class _EmployeListState extends State<EmployeList> {
  final searchController = TextEditingController();
  bool isLoading = false;
  Timer? searchOnStoppedTyping;

  @override
  void initState() {
    context.read<EmployeeBloc>().add(LoadEmployeeEvent());
    super.initState();
  }

  @override
  void dispose() {
    searchController.dispose();
    super.dispose();
  }

  _onChange(value) {
    context.read<SearchBloc>().add(
        OnChangeEvent(context: context, searchController: searchController));
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: const Text('Liste des Employées'),
        actions: [
          IconButton(
              onPressed: () => Navigator.pushNamed(context, '/'),
              icon: const Icon(Icons.logout))
        ],
      ),
      body: Column(
        children: [
          Padding(
            padding:
                const EdgeInsets.only(top: 5, bottom: 5, left: 10, right: 10),
            child: TextField(
                controller: searchController,
                onChanged: _onChange,
                decoration: InputDecoration(
                    border: OutlineInputBorder(
                        borderRadius: BorderRadius.circular(8.0),
                        borderSide: BorderSide.none),
                    hintText: 'Search',
                    filled: true,
                    prefixIcon: const Icon(Icons.search),
                    fillColor: Colors.grey)),
          ),
          BlocBuilder<SearchBloc, SearchState>(builder: (context, state) {
            return state.isLoading
                ? const LinearProgressIndicator(
                    valueColor: AlwaysStoppedAnimation<Color>(Colors.white))
                : Container();
          }),
          Expanded(
            child: BlocBuilder<EmployeeBloc, EmployeeState>(
              builder: (context, state) {
                if (state is LoadingEmployeeState) {
                  return const Center(
                    child: CircularProgressIndicator(),
                  );
                } else if (state is LoadingEmployeeSuccessState) {
                  return EmployeeListWidget(employes: state.listEmployee);
                } else if (state is LoadingEmployeeErrorState) {
                  return Center(
                    child: Text(state.errorMessage),
                  );
                } else if (state is SearchEmployeeErrorState) {
                  return Center(
                    child: Text(state.errorMessage),
                  );
                } else if (state is SearchEmployeeSuccessState) {
                  return EmployeeListWidget(employes: state.listEmployee);
                } else {
                  return Container();
                }
              },
            ),
          ),
        ],
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () => Navigator.pushNamed(context, '/addEmp'),
        child: const Icon(Icons.add),
      ),
    );
  }
}
