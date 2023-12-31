part of 'sign_up_bloc.dart';

@immutable
abstract class SignUpState {
  final User user;

  const SignUpState({required this.user});
}

class SignUpInitial extends SignUpState {
   SignUpInitial() : super(user: User(id:"",email: "",password: "",name: "",roles: ["USER"]));
}

class SignedUpState extends SignUpState {
  const SignedUpState({required super.user});
}

class SignUpErrorState extends SignUpState {
  final String errorMessage;
  SignUpErrorState({required this.errorMessage})
      :  super(user: User(id:"", email: "",password: "",name: "",roles: ["USER"]));
}
