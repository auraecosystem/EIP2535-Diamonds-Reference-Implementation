git clone git@github.com:mudgen/diamond-2-hardhat.git
cd diamond-2-hardhat
npm install
npx hardhat test
   $ git clone https://www.github.com/ArcStreetCapital/Diamond-Foundry.git
   $ forge script script/deployDiamond.s.sol:DeployScript --rpc-url $GOERLI_RPC_URL --broadcast --verify -vvvv --ffi
   $ forge script script/deployDiamond.s.sol:DeployScript --fork-url http://localhost:8545 --broadcast --ffi
   $ forge test --ffi --match-path test/DiamondTests.t.sol
   $ forge install
