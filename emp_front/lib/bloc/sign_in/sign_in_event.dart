part of 'sign_in_bloc.dart';

@immutable
abstract class SignInEvent {}

class SignedInEvent extends SignInEvent {
  final String username;
  final String password;

  SignedInEvent({required this.username,required this.password});
}
