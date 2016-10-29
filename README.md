# lEarnit

lEarnit is an Android application powered by Paytm which motivates learning in students by rewarding them for studying<br>
It contains various study modules which have a reward amount associated with them.<br>
On completing a module, payment is sent from parent’s Paytm wallet to student’s wallet.<br>

PAYMENT MODEL
=============
It uses Paytm's [P2P payment model](https://goo.gl/UI1hl7) which is not out in public yet.<br>
  - Both parent and student register on student's device.
  - App gets access_token for both of them.
  - Student selects a module to solve questions.
  - At the end of module according to correct questions the amount that should be received is displayed.
  - Student initiates a Request Payment from his device.
  - Student enters OTP received on parent's device.
  - Money is transferred from parent's to student's wallet.

TODO
====
  - [x] Add fields verification in LoginActivity
  - [x] Remove hardcoded strings
  - [ ] Handle both parent's and student's login gracefully (Currently uses hacked solution)
  - [ ] Handle different types of questions (Lack of time forced to use only Single choice questions)
  - [ ] Improve UI
  - [ ] Add frequently searched products over web and show it in wallet section (Currently mocked)

MADE WITH ❤ BY
====
[@grgvineet](https://github.com/grgvineet), [@aayusharora](https://github.com/aayusharora) and [@the-dagger](https://github.com/the-dagger)
