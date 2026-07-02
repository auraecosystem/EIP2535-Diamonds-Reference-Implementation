package io.fdka.editor.figures;

import CH.ifa.draw.contrib.DiamondFigure;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class CryptoDecisionNode extends DiamondFigure {

    public enum DecisionType {
        VERIFY_SIGNATURE,
        CHECK_BALANCE,
        CHECK_NONCE,
        VALIDATE_GAS,
        VERIFY_BLOCK,
        VERIFY_TRANSACTION,
        EXECUTE_CONTRACT,
        MULTISIG_CHECK,
        CONSENSUS,
        CUSTOM
    }

    private String id;
    private String title;
    private DecisionType type;
    private String smartContract;
    private String chain;
    private Map<String, Object> parameters = new HashMap<>();

    public CryptoDecisionNode() {
        super(new Point(100,100), new Point(220,180));

        id = java.util.UUID.randomUUID().toString();
        title = "Decision";
        type = DecisionType.CUSTOM;
        chain = "FDKA";
    }

    public CryptoDecisionNode(
            Point origin,
            Point corner,
            DecisionType type,
            String title) {

        super(origin, corner);

        this.id = java.util.UUID.randomUUID().toString();
        this.type = type;
        this.title = title;
        this.chain = "FDKA";
    }

    @Override
    public void draw(Graphics g) {

        super.draw(g);

        var box = displayBox();

        g.setColor(Color.BLACK);

        g.drawString(
            title,
            box.x + 10,
            box.y + box.height / 2
        );

        g.drawString(
            "[" + chain + "]",
            box.x + 10,
            box.y + box.height - 10
        );
    }

    public boolean evaluate(TransactionContext tx) {

        switch(type) {

            case VERIFY_SIGNATURE:
                return tx.verifySignature();

            case CHECK_BALANCE:
                return tx.getBalance() >= tx.getAmount();

            case CHECK_NONCE:
                return tx.verifyNonce();

            case VALIDATE_GAS:
                return tx.verifyGas();

            case VERIFY_BLOCK:
                return tx.verifyBlock();

            case VERIFY_TRANSACTION:
                return tx.verifyTransaction();

            case EXECUTE_CONTRACT:
                return tx.executeContract(smartContract);

            case MULTISIG_CHECK:
                return tx.verifyMultiSig();

            case CONSENSUS:
                return tx.verifyConsensus();

            default:
                return false;
        }
    }

    public void setParameter(String key,Object value){
        parameters.put(key,value);
    }

    public Object getParameter(String key){
        return parameters.get(key);
    }

    public String getNodeId(){
        return id;
    }

    public DecisionType getDecisionType(){
        return type;
    }

    public void setDecisionType(DecisionType type){
        this.type = type;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setChain(String chain){
        this.chain = chain;
    }

    public String getChain(){
        return chain;
    }

    public void setSmartContract(String contract){
        this.smartContract = contract;
    }
}
