part of 'sign_in_bloc.dart';

@immutable
abstract class SignInState {
  final String username;
  final String password;

  const SignInState({required this.username, required this.password});
}

class SignInInitial extends SignInState {
  const SignInInitial() : super(username: "", password: "");
}

class SignedInState extends SignInState {
  const SignedInState({required super.username, required super.password});
}

class SignInErrorState extends SignInState {
  final String errorMessage;
  const SignInErrorState({required this.errorMessage})
      : super(username: "", password: "");
}
