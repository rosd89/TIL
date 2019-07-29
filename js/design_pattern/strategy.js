const ExContext = class {
  constructor(data) {
    let strategy;
    if (data.startsWith('{') || data.startsWith('[')) {
      strategy = new JsonParser();
    } else {
      strategy = new CsvParser();
    }

    Object.assign(this, {
      data, strategy
    })
  }

  getData() {
    return this.strategy.parse(this.data);
  }
};

const ParserStrategy = class {
  parse(data) {
    throw new Error('Must be override');
  }
};

const CsvParser = class extends ParserStrategy {
  parse(data) {
    return data.split(',');
  }
};

const JsonParser = class extends ParserStrategy {
  parse(data) {
    return JSON.parse(data);
  }
};

(_ => {
  const csv = 'a,b,c,d,f,g,e';
  const ec1 = new ExContext(csv);
  console.log(csv);
  console.log(ec1.getData());

  const json = '["a","b","c","d","f","g","e"]';
  const ec2 = new ExContext(json);
  console.log(json);
  console.log(ec2.getData())
})();