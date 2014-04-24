import Prelude hiding ((^), replicate)


data Snapper = OFF | ON deriving Eq
solve n k = if chk (sim n k) then "ON"
                             else "OFF"
chk x = all (== ON) x
f ^ k = if k == 0 then id
                  else f . (f ^ (k - 1))
replicate n a = if n == 0 then []
                          else a : (replicate (n - 1) a)

instance S Snapper where
    next ON  = OFF
    next OFF = ON
    prop ON  = 1
    prop OFF = 0

sim n k = sitr' k (replicate n OFF)

type Nat = Int -- Int で代用
class S s where
    next :: s -> s
    prop :: s -> Nat

sitr' n (a : x) = (nitr' n a) : (sitr' (psum' n a) x)
sitr' n []      = []
nitr' n a = if n < r a then nitr n a
                       else nitr (mod (n - r a) (p a) + r a) a
psum' n a = if n < r a
                then psum n a
                else div (n - r a) (p a) * psum (p a) (nitr (r a) a)
                         + psum (mod (n - r a) (p a)) (nitr (r a) a)
                         + psum (r a) a

nitr n a = (next ^ n) a
psum n a = if n == 0 then 0
                     else prop (nitr (n - 1) a) + psum (n - 1) a
p a = n a - r a
r a = sub 0
    where sub m = if nitr m a == nitr (n a) a then m
                                              else sub (m + 1)
n a = sub 1
    where sub m = if elem (nitr m a) (c a m) then m
                                             else sub (m + 1)
c a m = if m == 0 then []
                  else (nitr (m - 1) a) : c a (m - 1)
