package design3.version2;

import design3.util.PointGameAlgorithm;
import design3.version1.Version1Controller;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * @author Minghua Zhou 周明华
 * @version 1.0
 * @date 2019/12/12 11:06
 * @modified by:
 */
public class Version2Controller extends Version1Controller {

    @FXML
    protected TextField resultShow;
    protected boolean refreshFlag = true;
    protected String[] result;
    protected int count = 0;

    public void pressFind() {
        if (refreshFlag) {
            int[] cards = super.cardNumbers;
            for (int i = 0; i < cards.length; i++) {
                cards[i] = cards[i] % 13;
                if (cards[i] == 0) {
                    cards[i] = 13;
                }
            }

            result = PointGameAlgorithm.getResultStrings(cards);
            refreshFlag = false;
            count = 0;
        }

        if (result.length > 0) {
            count %= result.length;
            this.resultShow.setText(result[count++]);
        } else {
            this.resultShow.setText("No solution");
        }
    }

    @Override
    public void pressRefresh() {
        super.pressRefresh();
        this.resultShow.setText("");
        this.refreshFlag = true;
    }
}
