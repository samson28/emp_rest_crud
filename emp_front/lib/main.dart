import 'package:emp_front/bloc/employee/employee_bloc.dart';
import 'package:emp_front/bloc/search/search_bloc.dart';
import 'package:emp_front/bloc/sign_in/sign_in_bloc.dart';
import 'package:emp_front/bloc/sign_up/sign_up_bloc.dart';
import 'package:emp_front/provider/hive_provider.dart';
import 'package:emp_front/utilities/root_view.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

void main() {
  WidgetsFlutterBinding.ensureInitialized();
  HiveProvider.init();
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MultiBlocProvider(
      providers: [
        BlocProvider(create: (context) => EmployeeBloc()),
        BlocProvider(create: (context) => SearchBloc()),
        BlocProvider(create: (context) => SignInBloc()),
        BlocProvider(create: (context) => SignUpBloc())
      ],
      child: rootView(),
    );
  }


}
