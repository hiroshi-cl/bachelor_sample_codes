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

sim n k = (snap ^ k) (replicate n OFF)

type Nat = Int -- Int で代用
class S s where
    next :: s -> s
    prop :: s -> Nat

snap (a : x) = next a : (snap ^ prop a) x
snap []      = []
