part of 'search_bloc.dart';

@immutable
abstract class SearchEvent {}

class OnChangeEvent extends SearchEvent {
  final TextEditingController searchController;
  final BuildContext context;
  OnChangeEvent({required this.searchController, required this.context});
}
