#import "template.typ": *

#show: project.with(
  title: "OOAD Assignment 2 UML Diagram",
  authors: (
    (
      name: "田所浩二",
      email: "114514@mail.sustech.edu.cn"
    ),
  ),
)

= Use Case Diagram

#par(first-line-indent: 0em, "Note: Though visitors and administrators can view reservation records, visitors are limited to viewing their own records, while administrators can view all records.")

#figure(
  image("images/usecase.svg")
)

= Class Diagram

#align(center, table(
  columns: (auto, 1fr, 0.7fr),
  align: horizon,
  table.header( [*Methods*], [*Parameters*], [*Return Value*] ),
  [`searchReserveRecord`], [None], [`List<ReservationRecord>` A list of ReservationRecord objects of visitor],
  [`cancel`], [`reservation: Reservation` A Reservation object. Cancel should be invoked before `viewReservationRecords` since it returns a list of valid objects], [`boolean` Status of cancel \ `True`: cancelled successfully \ `False`: invalid due to some reason],
  table.cell(rowspan: 2, [`reserve`]), [`session: Session` A Session object when visitor chooses a session of a performace], table.cell(rowspan: 2, [`boolean` Status of reserve \ `True`: Successfully booked \ `False`: At least one seat is invalid, or the session is invalid]), [`seats: List<Seat>` List of seat objects that the visitor wants to select],
  table.cell(rowspan:2 , [`releasePerformance`]), [`name: String` \ Name of performace], table.cell(rowspan: 2, [`Performance` A performace object. Can be modified by only updated to system by invoking the methods]), [`description: String` \ Description of performance], 
  [`updatePerformance`], [`performace: Performance` Performance object to be updated. Change the values in that object before invoking this method.], [`boolean` Status of update. \ `True`: updated successfully \ `False`: invalid update],
  [`createSession`], [`session: Session` Session to be created and released. New a Session object and fill up the values before creating.], [`boolean` Status of create \ `True`: created successfully \ `False`: invalid create],
  [`updateSession`], [`session: Session` Session to be updated. Similar to updatePerformace], [`boolean` Status of update \ `True`: updated successfully \ `False`: invalid update],
  [`cancelSession`], [`session: Session` Session to be cancelled. Note that the object will be deleted and the status in ReservationRecord will be changed.], [`boolean` Status of cancel \ `True`: Cancelled successfully \ `False`: invalid cancel],
  table.cell(rowspan: 2, [`viewRecord`]), [`startDate: Date` Start of date to search], table.cell(rowspan: 2, [`List<ReservationRecord>` Search results.]), [`endDate: Date` End of date to search]
))

#figure(
  image("images/class.svg")
)

