import Prelude hiding ((^), replicate)

data Snapper = OFF | ON deriving Eq
solve n k = if chk (sim n k) then "ON"
                             else "OFF"
chk x = all (== ON) x

sim n k = (snap ^ k) (replicate n OFF)
f ^ k = if k == 0 then id
                  else f . (f ^ (k - 1))
snap (a : x) = if a == ON then (OFF : snap x)
                          else (ON : x)
snap []      = []
replicate n a = if n == 0 then []
                          else a : (replicate (n - 1) a)
