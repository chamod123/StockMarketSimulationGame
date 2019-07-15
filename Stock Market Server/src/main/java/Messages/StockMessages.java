package Messages;

import Model.Stock;

import java.io.Serializable;

public interface StockMessages {

    class ActionPerformed implements Serializable {
        private static final long serialVersionUID = 1L;
        private final String description;

        public ActionPerformed(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }


    class CreateStockMessage implements Serializable {

        private static final long serialVersionUID = 1L;
        private final Stock stock;

        public CreateStockMessage(Stock stock) {
            this.stock = stock;
        }

        public Stock getStock() {
            return stock;
        }
    }

    class GetStockMessage implements Serializable {
        private static final long serialVersionUID = 1L;
        private final Long StockId;

        public GetStockMessage(Long StockId) {
            this.StockId = StockId;
        }

        public Long getStockId() {
            return StockId;
        }

    }

    class GetStockSectorMessage implements Serializable {
        private static final long serialVersionUID = 1L;
        private final Long sector;

        public GetStockSectorMessage(Long sector) {
            this.sector = sector;
        }

        public Long getSector() {
            return sector;
        }

    }


}